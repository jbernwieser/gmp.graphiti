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
package org.eclipse.graphiti.features.context;

import org.eclipse.graphiti.mm.pictograms.Anchor;

/**
 * The Interface IConnectionContext.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IConnectionContext extends IContext {

	/**
	 * Gets the source anchor.
	 * 
	 * @return the source anchor
	 */
	Anchor getSourceAnchor();

	/**
	 * Gets the target anchor.
	 * 
	 * @return the target anchor
	 */
	Anchor getTargetAnchor();

}
