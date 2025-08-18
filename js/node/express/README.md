Network

    Packets are data transfer units. Consist of 5 layers:
        
    ^    Application -> HTTP, SMTP, FTP, SSH
    |
    |    Transport -> UDP / TCP
    |
    |    Network -> IP
    |
    |    Link -> wifi / ethernet
    |
    |    Physical - cables         
        
    The Network + Transport Layer = Internet protocol suite, TCP/IP

        UDP

            Lightweight - (headers are only 8 bytes)

            Connectionless - receiver does have to accept a connection

            Consistency?
                - send data no matter what (consistent)
                - packet loss ( no retry )
                - packets can arrive out of order

        TCP

            Connection-Based - 3 way handshake.

            Reliable
                - delivery acknowledgement
                - Retransmission
                - in-order packets
            
            Congestion control
                - can introduce latency to avoid packet loss

    HTTP

        🔹 HTTP/1.1

            Introduced persistent connections by default.

            The TCP connection remains open for multiple HTTP request/response cycles.

            Reuse is possible unless Connection: close is specified.

            But: requests are handled one at a time — the client must wait for the response before sending the next request (no multiplexing).

        🔹 HTTP/2

            Also uses a single long-lived TCP connection, but:

            Allows multiplexing: multiple request/response pairs can be in flight at the same time.

            Much more efficient than HTTP/1.1 for modern applications.

        🔹 HTTP/3

            Uses QUIC over UDP (not TCP).

            Supports connection reuse and multiplexing similar to HTTP/2, but with reduced latency and faster connection establishment.

        Efficient
            - Only connects when required.
        Stateless
            - No dialogue. Machines only know what they requested at the moment.

        HTTP messages

            Consist of a start line, headers (metadata. a blank line determines the end of the header), body

            Request

                GET /index.html HTTP/1.1       ← Request line
                Host: example.com              ← Headers
                User-Agent: Mozilla/5.0
                Accept: text/html

                [Optional body]                ← Body (used in POST/PUT requests)

                Key Parts:

                    Request Line: Method (GET, POST, PUT, etc.), path, and HTTP version.

                    Headers: Metadata (e.g., content type, authentication).

                    Body (optional): Data being sent (e.g., form fields, JSON, files).

            Response

                HTTP/1.1 200 OK                ← Status line
                Content-Type: text/html        ← Headers
                Content-Length: 1234

                <html>...</html>               ← Body (the actual content)

                Key Parts:

                    Status Line: Protocol version, status code (e.g., 200, 404), and message.

                    Headers: Describe the response (e.g., content type, caching).

                    Body: Actual content (HTML, JSON, file, etc.).



        

