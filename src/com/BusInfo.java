package com;

public class BusInfo {
   String ticketnumber;
   String departure;
    String destination;
    String date;
    String ampm;
    String time;
    String hour;
    String grade;
    String adultfare;
    String childfare;
    int seat1;
    int seat2;
    int seat3;
    int seat4;
    int seat5;
    int seat6;
    int seat7;
    int seat8;
    
    public BusInfo(String ticketnumber, String departure, String destination, String date, String ampm, String time, String hour, String grade,
          String adultfare, String childfare, int seat1, int seat2, int seat3, int seat4, int seat5, int seat6, int seat7, int seat8) {
       this.ticketnumber = ticketnumber;
       this.departure = departure;
       this.destination = destination;
       this.date = date;
       this.ampm = ampm;
       this.time = time;
       this.hour = hour;
       this.grade = grade;
       this.adultfare = adultfare;
       this.childfare = childfare;
       this.seat1 = seat1;
       this.seat2 = seat2;
       this.seat3 = seat3;
       this.seat4 = seat4;
       this.seat5 = seat5;
       this.seat6 = seat6;
       this.seat7 = seat7;
       this.seat8 = seat8;
    }
    
    public String getTicketnumber() { return ticketnumber; }
    public void setTicketnumber(String ticketnumber) { this.ticketnumber = ticketnumber; }
    
    public String getDeparture() { return departure; }
    public void setDeparture(String departure) { this.departure = departure; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    
    public String getAmpm() { return ampm; }
    public void setAmpm(String ampm) { this.ampm = ampm; }
    
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    
    public String getHour() { return hour; }
    public void setHour(String hour) { this.hour = hour; }
    
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
    
    public String getAdultfare() { return adultfare; }
    public void setAdultfare(String adultfare) { this.adultfare = adultfare; }
    
    public String getChildfare() { return childfare; }
    public void setChildfare(String childfare) { this.childfare = childfare; }
    
    public int getSeat1() { return seat1; }
    public void setSeat1(int seat1) { this.seat1 = seat1; }
    
    public int getSeat2() { return seat2; }
    public void setSeat2(int seat2) { this.seat2 = seat2; }
    
    public int getSeat3() { return seat3; }
    public void setSeat3(int seat3) { this.seat3 = seat3; }
    
    public int getSeat4() { return seat4; }
    public void setSeat4(int seat4) { this.seat4 = seat4; }
    
    public int getSeat5() { return seat5; }
    public void setSeat5(int seat5) { this.seat5 = seat5; }
    
    public int getSeat6() { return seat6; }
    public void setSeat6(int seat6) { this.seat6 = seat6; }
    
    public int getSeat7() { return seat7; }
    public void setSeat7(int seat7) { this.seat7 = seat7; }
    
    public int getSeat8() { return seat8; }
    public void setSeat8(int seat8) { this.seat8 = seat8; }
    
    // DTO 객체 확인
    // 이클립스 팁 : toString()
    // 자동 생성 : 우클릭 → source → Generate toString → [OK]
    public String toString() {
        return "BusDTO [ticketnumber=" + ticketnumber + ", departure=" + departure + ", destination=" + destination
                    + ", date=" + date + ", ampm=" + ampm + ", time=" + time + "," + ", hour=" + hour + ","
                    + "grade=" + grade + ", adultfare=" + adultfare + ", childfare=" + childfare + ","
                    + "seat1=" + seat1 + ", seat2=" + seat2 + ", seat3=" + seat3 + ", seat4=" + seat4 + ","
                    + "seat5=" + seat5 + ", seat6=" + seat6 + ", seat7=" + seat7 + ", seat8=" + seat8 + "]";
    }
}