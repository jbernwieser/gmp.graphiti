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
 *
 * </copyright>
 *
 *******************************************************************************/
/*
 * Created on 17.11.2005
 */
package org.eclipse.graphiti.features.context;

import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Interface ICreateConnectionContext.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface ICreateConnectionContext extends IContext, IConnectionContext {

	/**
	 * Gets the source pictogram element.
	 * 
	 * @return the source pictogram element
	 */
	PictogramElement getSourcePictogramElement();

	/**
	 * Gets the target pictogram element.
	 * 
	 * @return the target pictogram element
	 */
	PictogramElement getTargetPictogramElement();

	/**
	 * @return the sourceLocation
	 * @since 0.8
	 */
	ILocation getSourceLocation();

	/**
	 * @return the targetLocation
	 * @since 0.8
	 */
	ILocation getTargetLocation();
}