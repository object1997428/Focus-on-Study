window.addEventListener('load', function () {
    showClock();
});

let Target = document.querySelector('#clock');
let End = document.querySelector('#end_btn');

let hours = 0,
    minutes = 0,
    seconds = 0;

function showClock() {
    seconds += 1;
    if (seconds == 60) {
        (minutes += 1), (seconds = 0);
    }
    if (minutes == 60) {
        (hours += 1), (minutes = 0);
    }

    let msg = hours + '시 ';
    msg += minutes + '분 ';
    msg += seconds + '초';

    Target.innerText = msg;
}


var timer = setInterval(showClock, 1000);

End.addEventListener("click", () => {
    console.log("exit함수 시작\n");
    let form=document.createElement('form')

    let tt=String(hours*360+minutes*60+seconds);
    console.log(tt);
    let time=document.createElement('input');
    time.setAttribute('type','hidden');
    time.setAttribute('name','time');
    time.setAttribute('value',tt);

    form.appendChild(time);
    form.setAttribute('method','post');
    // form.setAttribute('target','_blank');
    form.setAttribute('action', '/exit');
    document.body.appendChild(form);

    clearInterval(timer);
    console.log(hours + ':' + minutes + ':' + seconds);

    form.submit();
});
