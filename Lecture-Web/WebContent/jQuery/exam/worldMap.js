// 동적으로 표현할 위치를 그림
$(function () {
    $('.world').maphilight({
        strokeColor: '02CAC3',
        strokeOpacity: 1,
        shadow: true,
        fillColor: '5F5F5F',
        fillOpacity: 0.3,
        fade: false
    });
 
    $('area').qtip({
        style: {classes: "qtip-light qtip-rounded qtip-custom"}
    });
 
    $('area').attr('onclick', 'clickArea(this)');
});
 
var currentInterval;
function clickArea(nation) {
    clearInterval(currentInterval);
    var nation = nation.getAttribute('id');
    var nationSpan = document.getElementById("nation");
    var nationDescript = "";
    var timeDiff = 9;
 
    if (nation === "korea") {
        nationDescript = "한국, 서울";
    } else if (nation === "china") {
        nationDescript = "중국, 베이징";
        timeDiff -= 1;
    } else if (nation === "japan") {
        nationDescript = "일본, 도쿄";
    } else if (nation === "india") {
        nationDescript = "인도, 뉴델리";
        timeDiff -= 3;
    } else if (nation === "italy") {
        nationDescript = "이태리, 로마";
        timeDiff -= 7;
    } else if (nation === "spain") {
        nationDescript = "스페인, 마드리드";
        timeDiff -= 7;
    } else if (nation === "england") {
        nationDescript = "영국, 런던";
        timeDiff -= 8;
    } else if (nation === "washington") {
        nationDescript = "미국, 뉴욕/워싱턴";
        timeDiff -= 13;
    } else if (nation === "LA") {
        nationDescript = "미국, 캘리포니아주";
        timeDiff -= 16;
    } else if (nation === "brazil") {
        nationDescript = "브라질, 상파울루";
        timeDiff -= 12;
    } else if (nation === "santiago") {
        nationDescript = "칠레, 산티아고";
        timeDiff -= 13;
    } else if (nation === "southAfrica") {
        nationDescript = "남아프리카공화국, 케이프타운";
        timeDiff -= 7;
    } else if (nation === "australia") {
        nationDescript = "오스트레일리아, 캔버라";
        timeDiff += 1;
    } else if (nation === "alaska") {
        nationDescript = "미국, 알래스카";
        timeDiff -= 17;
    } else if (nation === "moscow") {
        nationDescript = "러시아, 모스크바";
        timeDiff -= 6;
    }
 
    nationSpan.innerHTML = nationDescript;
    showClock(timeDiff);
    currentInterval = setInterval("showClock("+timeDiff+")", 1000);
}
 
function showClock(diff) {
    var clockDiv = document.getElementById("clockDiv");
    var clock = new Date();
    var timeZone = clock.getTime() + (clock.getTimezoneOffset() * 60000) + (diff * 3600000);
    clock.setTime(timeZone);
 
    var apm = 'AM';
    var hour = clock.getHours();
    var minute = addZero(clock.getMinutes());
    var seconds = addZero(clock.getSeconds());
 
    // 정오의 경우...
    if (hour > 12) {
        hour = hour - 12;
        apm = "PM";
    } else if (hour == 12) {
        apm = "PM";
    }
 
    hour = addZero(hour);
    clockDiv.innerHTML = hour + ":" + minute + ":" + seconds + " " + apm;
}
 
function addZero(num) {
    num = num.toString();
    if (num.length < 2) {
        num = 0 + num;
    }
    return num;
}