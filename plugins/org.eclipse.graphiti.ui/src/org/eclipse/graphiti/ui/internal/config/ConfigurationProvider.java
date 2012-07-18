/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    Bug 336488 - DiagramEditor API
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.config;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.internal.IResourceRegistry;
import org.eclipse.graphiti.ui.internal.ResourceRegistry;
import org.eclipse.graphiti.ui.internal.contextbuttons.IContextButtonManager;
import org.eclipse.graphiti.ui.internal.partfactory.PictogramsEditPartFactory;
import org.eclipse.graphiti.ui.internal.policy.DefaultEditPolicyFactory;
import org.eclipse.graphiti.ui.internal.policy.IEditPolicyFactory;
import org.eclipse.ui.IWorkbenchPart;

/**
 * A concrete implementation of the interface IConfigurationProviderInternal
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ConfigurationProvider implements IConfigurationProviderInternal {
	private boolean _isDisposed = false;

	private IEditPolicyFactory _editPolicyFactory;

	private IEditPartFactory _editPartFactory;

	private IWorkbenchPart _workbenchPart;

	private DiagramEditor diagramEditor;

	private IDiagramTypeProvider diagramTypeProvider;


	private IContextButtonManager contextButtonManager;

	private IResourceRegistry resourceRegistry = new ResourceRegistry();

	/**
	 * The Constructor.
	 * 
	 * @param diagramEditor
	 *            the diagram editor
	 * @param diagramTypeProvider
	 *            the diagram type provider
	 */
	public ConfigurationProvider(DiagramEditor diagramEditor, IDiagramTypeProvider diagramTypeProvider) {
		this.diagramEditor = diagramEditor;
		setDiagramTypeProvider(diagramTypeProvider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.ui.config.provider.IConfigurationProvider#dispose()
	 */
	public void dispose() {
		_editPolicyFactory = null;
		_editPartFactory = null;

		_isDisposed = true;

		if (resourceRegistry != null) {
			resourceRegistry.dispose();
		}

		getDiagramTypeProvider().dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.ui.config.provider.IConfigurationProvider#isDisposed
	 * ()
	 */
	public boolean isDisposed() {
		return _isDisposed;
	}

	public IContextButtonManager getContextButtonManager() {
		return contextButtonManager;
	}

	public void setContextButtonManager(IContextButtonManager contextButtonManager) {
		this.contextButtonManager = contextButtonManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.ui.config.provider.IConfigurationProvider#
	 * getEditPolicyFactory()
	 */
	public IEditPolicyFactory getEditPolicyFactory() {
		if (_editPolicyFactory == null && !_isDisposed) {
			_editPolicyFactory = new DefaultEditPolicyFactory(this);
		}
		return _editPolicyFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.ui.config.provider.IConfigurationProvider#
	 * getEditPartFactory()
	 */
	public IEditPartFactory getEditPartFactory() {
		if (_editPartFactory == null && !_isDisposed) {
			_editPartFactory = new PictogramsEditPartFactory(this);
		}
		return _editPartFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.ui.config.provider.IConfigurationProvider#
	 * setWorkbenchPart(org.eclipse.ui.IWorkbenchPart)
	 */
	public void setWorkbenchPart(IWorkbenchPart workbenchPart) {
		if (_workbenchPart != null && (!_workbenchPart.equals(workbenchPart)))
			throw new RuntimeException("The IWorbenchPart must not be changed after setting it initially."); //$NON-NLS-1$
		_workbenchPart = workbenchPart;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.ui.config.provider.IConfigurationProvider#
	 * getWorkbenchPart()
	 */
	public IWorkbenchPart getWorkbenchPart() {
		return _workbenchPart;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.ui.internal.config.IConfigurationProviderInternal#
	 * getDiagramEditor()
	 */
	public DiagramEditor getDiagramEditor() {
		return diagramEditor;
	}

	// ===================== overwriteable behaviour ==========================

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.ui.internal.config.IConfigurationProviderInternal#
	 * getDiagram()
	 */
	public Diagram getDiagram() {
		return getDiagramTypeProvider().getDiagram();
	}

	private void setDiagramTypeProvider(IDiagramTypeProvider diagramTypeProvider) {
		this.diagramTypeProvider = diagramTypeProvider;
	}

	/**
	 * Gets the diagram type provider.
	 * 
	 * @return Returns the diagramTypeProvider.
	 */
	public IDiagramTypeProvider getDiagramTypeProvider() {
		return diagramTypeProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.ui.internal.config.IConfigurationProviderInternal#
	 * getFeatureProvider()
	 */
	public IFeatureProvider getFeatureProvider() {
		return getDiagramTypeProvider().getFeatureProvider();
	}

	public IResourceRegistry getResourceRegistry() {
		return resourceRegistry;
	}
}