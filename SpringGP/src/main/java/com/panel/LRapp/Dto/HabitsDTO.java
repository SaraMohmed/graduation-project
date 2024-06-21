package com.panel.LRapp.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class HabitsDTO {


        private int id;
        private List<String> title;
        private Date startDate;
        private Date endDate;
        private boolean done;

        public HabitsDTO(List<String> title, Date startDate, Date endDate, boolean done) {
            this.title = title;
            this.startDate = startDate;
            this.endDate = endDate;
            this.done = done;
        }
}
