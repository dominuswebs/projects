const duration = 20_000;

const actions = [
    { at: 0,  action: () => console.log('start') },
    { at: 5_000, action: () => console.log('5 seconds') },
    { at: 10_000, action: () => console.log('10 seconds') },
    { at: 15_000, action: () => console.log('15 seconds') }
];

let previous = Date.now() % duration;

function timeline() {
    
    const current = Date.now() % duration;

    const wrapped = current < previous;

    for (const item of actions) {
        const crossed =
            (!wrapped && previous < item.at && current >= item.at) ||
            (wrapped && (item.at > previous || item.at <= current));

        if (crossed) {
            item.action();
        }
    }

    previous = current;

    requestAnimationFrame(timeline);
}

timeline();
export { timeline }