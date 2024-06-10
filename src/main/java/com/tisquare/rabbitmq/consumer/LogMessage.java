package com.tisquare.rabbitmq.consumer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogMessage implements Serializable {

    //TODO datetime, level, taskName, hostName, userId, msg
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime dateTime;

    private String level;
    private String taskname;
    private String hostname;
    private String userId;
    private String msg;

    public  LogMessage() {}

    public LogMessage(LocalDateTime dateTime, String level, String taskname, String hostname, String userId, String msg) {
        this.dateTime = dateTime;
        this.level = level;
        this.taskname = taskname;
        this.hostname = hostname;
        this.userId = userId;
        this.msg = msg;
    }

    // "[datetime] [level] [taskname] [hostname] [userId] - msg" 형식으로 파싱
    public LogMessage(String logMessageStr) {
        String[] parts = logMessageStr.split(" - ", 2);
        String msg = parts[1];
        String[] fields = parts[0].substring(1, parts[0].length() - 1).split("\\] \\[");

        this.dateTime = LocalDateTime.parse(fields[0], DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"));
        this.level = fields[1];
        this.taskname = fields[2];
        this.hostname = fields[3];
        this.userId = fields[4];
        this.msg = msg;
    }

    @Override
    public String toString() {
        return String.format("[%s] [%s] [%s] [%s] [%s] - %s",
                dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")),
                level, taskname, hostname, userId, msg);
    }

}
