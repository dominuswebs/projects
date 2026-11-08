1. The order matters.

The HTTP and WebSocket listeners are registered before navigation, so they can capture activity from the initial page load.

The MutationObserver is registered after DOMContentLoaded. If the page modifies its DOM before that point, those earlier mutations won't be detected.

2. You may see a lot of output.

The observer detects every matching DOM mutation, including changes to attributes and text. A page with frequent animations or live updates can generate many events.

Initially, I'd leave it as shown. Once you identify the relevant activity, you can filter out unnecessary events.

3. This monitors the page, not every browser connection.

Playwright's response event captures HTTP responses associated with the page, while the WebSocket listener captures WebSocket connections created by that page. Other browser contexts or service workers may require additional investigation if they are involved.