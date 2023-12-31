<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <script type="text/javascript">
        let ws = null;

        function connect() {
            if (ws) {
                ws.close();
            }

            ws = new WebSocket("ws://localhost:8087/lab16/websocket");

            ws.onmessage = function (event) {
                document.getElementById('resultPanel').innerHTML += event.data + "<br>";
            };
        }

        function disconnect(){
            ws.close();
        }
    </script>
</head>
<body>
<button onclick="connect()">START</button>
<button onclick="disconnect();">STOP</button>
<div id="resultPanel"></div>
</body>
</html>