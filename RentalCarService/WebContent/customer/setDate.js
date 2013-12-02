function setDates() {
    var date = new Date();
    var minutes = date.getMinutes();
    minutes = Math.ceil(minutes/15);
    minutes *= 15;
    date.setMinutes(minutes);
    
    var futureDate = new Date();
    futureDate.setMinutes(date.getMinutes());
    futureDate.setHours(date.getHours() + 1);
    
    var today = date.toJSON().slice(0,10);
    var now = date.toTimeString().slice(0,5);
    var oneHourInFuture = futureDate.toTimeString().slice(0,5);
    
    var startDate = document.getElementById("startDate");
    startDate.value = today;
    startDate.min = today;
    
    var endDate = document.getElementById("endDate");
    endDate.value = today;
    endDate.min = today;
    
    var startTime = document.getElementById("startTime");
    startTime.value = now;
    startTime.min = now;
    
    var endTime = document.getElementById("endTime");
    endTime.value = oneHourInFuture;
    endTime.min = oneHourInFuture;
}