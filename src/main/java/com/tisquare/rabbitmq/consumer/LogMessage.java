package com.tisquare.rabbitmq.consumer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogMessage implements Serializable {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    private String level;
    private String taskname;
    private String hostname;
    private String userId;
    private Long iuid;
    private String msg;



    public LogMessage() {}

    public LogMessage(LocalDateTime dateTime, String level, String taskname, String hostname, String userId, Long iuid, String msg) {
        this.dateTime = dateTime;
        this.level = level;
        this.taskname = taskname;
        this.hostname = hostname;
        this.userId = userId;
        this.iuid = iuid;
        this.msg = msg;
    }

//    public byte[] toJsonBytes() {
//        Map<String, Object> logData = new HashMap<>();
//        logData.put("dateTime", dateTime);
//        logData.put("userId", userId);
//        logData.put("level", level);
//        logData.put("msg", msg);
//        logData.put("iuid", iuid);
//        logData.put("hostname", hostname);
//
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            return mapper.writeValueAsBytes(logData);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to convert log data to JSON", e);
//        }
//    }

//    private static final long serialVersionUID = 1L;
//
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime dateTime;
//
//    private String userId;
//    private String level;
//    private String msg;
//    private Long iuid;
//    private String cp_code;
//
//    public LogMessage() {}
//
//    public LogMessage(LocalDateTime dateTime, String userId, String level, String msg, Long iuid, String cp_code) {
//        this.dateTime = dateTime;
//        this.userId = userId;
//        this.level = level;
//        this.msg = msg;
//        this.iuid = iuid;
//        this.cp_code = cp_code;
//    }
//
//    public byte[] toJsonBytes() {
//        Map<String, Object> logData = new HashMap<>();
//        logData.put("dateTime", dateTime);
//        logData.put("userId", userId);
//        logData.put("level", level);
//        logData.put("msg", msg);
//        logData.put("iuid", iuid);
//        logData.put("cp_code", cp_code);
//
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            return mapper.writeValueAsBytes(logData);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to convert log data to JSON", e);
//        }
//    }
}
