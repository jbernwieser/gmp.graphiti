/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    jpasch - Bug 323025 ActionBarContributor clean up
 *    cbrand - Bug 385586 - Remove,Delete, Update Action (ContextMenu, ButtonPad etc.)
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.action;

import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.internal.services.GraphitiInternal;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.internal.Messages;
import org.eclipse.graphiti.ui.platform.IConfigurationProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class RemoveAction extends AbstractPreDefinedAction implements IContextAndFeatureProvider {
	public static final String TOOL_TIP = Messages.RemoveAction_0_xmsg;

	public static final String TEXT = Messages.RemoveAction_1_xfld;

	public static final String ACTION_ID = "predefined remove action"; //$NON-NLS-1$
	
	public static final String ACTION_DEFINITION_ID = "org.eclipse.graphiti.ui.internal.action.RemoveAction"; //$NON-NLS-1$

	public RemoveAction(IWorkbenchPart part, IConfigurationProvider configurationProvider) {
		super(part, configurationProvider);
		setId(ACTION_ID);
		setActionDefinitionId(ACTION_DEFINITION_ID);
		setText(TEXT);
		setToolTipText(TOOL_TIP);
	}

	public boolean isAvailable() {
		PictogramElement pe[] = getSelectedPictogramElements();
		for (int i = 0; i < pe.length; i++) {
			IRemoveContext context = new RemoveContext(pe[i]);
			IRemoveFeature removeFeature = getFeatureProvider().getRemoveFeature(context);
			if (removeFeature == null || !removeFeature.isAvailable(context)) {
				return false;
			}
		}

		return true;
	}

	@Override
	protected boolean calculateEnabled() {
		PictogramElement pe[] = getSelectedPictogramElements();
		IFeatureProvider featureProvider = getFeatureProvider();
		if (pe.length == 0 || featureProvider == null) {
			return false;
		}
		for (int i = 0; i < pe.length; i++) {
			if (GraphitiInternal.getEmfService().isObjectAlive(pe[i])) {

				IRemoveContext context = new RemoveContext(pe[i]);

				IRemoveFeature removeFeature = featureProvider.getRemoveFeature(context);
				if (removeFeature == null || !removeFeature.isAvailable(context))
					return false;

				if (!removeFeature.canRemove(context)) {
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public void run() {
		genericRun(this);

		setSelection(StructuredSelection.EMPTY);
	}

	public IFeature provideFeatureForContext(IContext context) {
		return getFeatureProvider().getRemoveFeature((IRemoveContext) context);
	}

	public IContext createContext(final PictogramElement pe) {
		return new RemoveContext(pe);
	}
}