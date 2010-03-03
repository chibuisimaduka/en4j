/*
 *  Copyright (C) 2010 Ruben Laguna <ruben.laguna@gmail.com>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.rubenlaguna.en4j.sync;

import org.openide.modules.ModuleInstall;
import java.lang.management.ManagementFactory;
import javax.management.ObjectName;
import javax.management.JMException;
import org.openide.util.Exceptions;

/**
 * Manages a module's lifecycle. Remember that an installer is optional and
 * often not needed at all.
 */
public class Installer extends ModuleInstall {
    final static SynchronizationMBeanImpl mbean = new SynchronizationMBeanImpl();

    @Override
    public void restored() {
        // By default, do nothing.
        // Put your startup code here.
        try { // Register MBean in Platform MBeanServer
            ManagementFactory.getPlatformMBeanServer().
                    registerMBean(mbean,new ObjectName("com.rubenlaguna.en4j.sync:type=SynchronizationMBeanImpl"));
        }catch(JMException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
