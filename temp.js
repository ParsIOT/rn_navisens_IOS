function Convert2zeroto360(h){
    while (true) {
        if (h < 0) {
            h += 360;
        } else if (h > 360) {
            h -= 360;
        } else {
            return h;
        }
    }
}

console.log(Convert2zeroto360(730))


function DegreeDiff(d1,d2) {
    diff = 0;
    diff = Math.abs(d1 - d2);
    if (diff > 180) {
        diff = 365 - diff;
    }
    return diff;
}

console.log(DegreeDiff(Convert2zeroto360(450),90))