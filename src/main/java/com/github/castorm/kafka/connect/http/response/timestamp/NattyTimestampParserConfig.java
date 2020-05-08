package com.github.castorm.kafka.connect.http.response.timestamp;

/*-
 * #%L
 * kafka-connect-http-plugin
 * %%
 * Copyright (C) 2020 CastorM
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import lombok.Getter;
import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;

import java.time.ZoneId;
import java.util.Map;
import java.util.Optional;

import static org.apache.kafka.common.config.ConfigDef.Importance.LOW;
import static org.apache.kafka.common.config.ConfigDef.Type.STRING;

@Getter
public class NattyTimestampParserConfig extends AbstractConfig {

    private static final String ITEM_TIMESTAMP_ZONE = "http.response.item.timestamp.parser.zone";

    private final Optional<ZoneId> timestampZoneId;

    NattyTimestampParserConfig(Map<String, ?> originals) {
        super(config(), originals);
        timestampZoneId = Optional.ofNullable(getString(ITEM_TIMESTAMP_ZONE)).map(ZoneId::of);
    }

    public static ConfigDef config() {
        return new ConfigDef()
                .define(ITEM_TIMESTAMP_ZONE, STRING, null, LOW, "Timestamp ZoneId, to be used only when timestamp doesn't include a timezone/offset reference");
    }
}