/*
 * Copyright (C) 2007-2019 Crafter Software Corporation. All Rights Reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.craftercms.social.util;

import org.craftercms.commons.ebus.annotations.EListener;
import org.craftercms.commons.ebus.annotations.EventHandler;
import org.craftercms.commons.ebus.annotations.EventSelectorType;
import org.craftercms.social.security.SecurityActionNames;
import org.craftercms.social.util.ebus.SocialEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.event.Event;

/**
 *
 */
@EListener
public class EventLogger {
    private Logger logger = LoggerFactory.getLogger(EventLogger.class);

    @EventHandler(ebus = "@socialReactor", event = SecurityActionNames.UGC_CREATE, type = EventSelectorType.REGEX)
    public void logEvent(Event<SocialEvent> source) {
        final SocialEvent event = source.getData();
        logger.debug("Event {} type {} object {} " + source.getId(), event.getType().getName(), event.toString());
    }
}
