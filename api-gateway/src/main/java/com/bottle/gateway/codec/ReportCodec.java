package com.bottle.gateway.codec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import lombok.extern.slf4j.Slf4j;
import com.bottle.gateway.dto.ReportsWrapper;

import java.io.IOException;

/**
 * Created by Sergey on 26.01.2017.
 */
@Slf4j
public class ReportCodec implements MessageCodec<ReportsWrapper, ReportsWrapper> {

    @Override
    public void encodeToWire(Buffer buffer, ReportsWrapper interaction) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonStr = objectMapper.writeValueAsString(interaction);
            int length = jsonStr.getBytes().length;

            // Write data into given buffer
            buffer.appendInt(length);
            buffer.appendString(jsonStr);
        } catch (JsonProcessingException e) {
          //  log.error("JSON process error", e);
        }
    }

    @Override
    public ReportsWrapper decodeFromWire(int position, Buffer buffer) {
        // Person command starting from this *position* of buffer
        int _pos = position;

        // Length of JSON
        int length = buffer.getInt(_pos);

        // Get JSON string by it`s length
        // Jump 4 because getInt() == 4 bytes
        String jsonStr = buffer.getString(_pos += 4, _pos += length);

        ReportsWrapper report = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (jsonStr != null && !jsonStr.isEmpty()) {
                report = mapper.readValue(jsonStr, ReportsWrapper.class);
            }
        } catch (IOException e) {
        //    log.error("IO error", e);
        }
        return report;
    }

    @Override
    public ReportsWrapper transform(ReportsWrapper reports) {
        return reports;
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }
}