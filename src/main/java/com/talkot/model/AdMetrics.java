package com.talkot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdMetrics {
    private int id;
    private String date;
    private int impressions;
    private int clicks;
    private float ctr;
}
