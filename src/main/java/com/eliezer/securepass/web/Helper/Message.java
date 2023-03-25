package com.eliezer.securepass.web.Helper;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@AllArgsConstructor
public class Message {
    private String content;

    private String type;
}
