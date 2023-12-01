package com.farm.management.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserSummary {
    private Long id;
    private String username;
    private String name;
    private String level;

    public UserSummary(Long id, String username, String name, String level) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.level = level;
    }
}
