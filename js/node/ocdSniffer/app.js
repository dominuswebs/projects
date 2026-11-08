// Bash
// npm install playwright
// npx playwright install chromium

import { chromium } from 'playwright';

const browser = await chromium.launch({
    headless: true
});

const page = await browser.newPage();

// 1. Monitor HTTP responses.
page.on('response', response => {
    console.log(
        '[HTTP]',
        response.status(),
        response.url()
    );
});

// 2. Monitor WebSocket connections and messages.
page.on('websocket', ws => {
    console.log('[WebSocket connected]', ws.url());

    ws.on('framereceived', event => {
        console.log('[WebSocket received]', event.payload);
    });
});

// 3. Navigate to the page.
await page.goto('https://example.com', {
    waitUntil: 'domcontentloaded'
});

// 4. Monitor DOM mutations.
await page.evaluate(() => {
    const observer = new MutationObserver(mutations => {
        console.log(
            '[DOM changed]',
            mutations.length,
            'mutations detected'
        );
    });

    observer.observe(document.documentElement, {
        childList: true,
        subtree: true,
        attributes: true,
        characterData: true
    });
});

// 5. Forward browser console messages to Node.js.
page.on('console', message => {
    console.log('[Browser]', message.text());
});

// Keep the browser running.
await new Promise(() => {});