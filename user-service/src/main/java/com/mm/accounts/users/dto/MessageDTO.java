package com.mm.accounts.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class MessageDTO {
    private String message;
    private Instant date;
}
