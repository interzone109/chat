   var stompClient = null;

     /*   function setConnected(connected) {
            document.getElementById('connect').disabled = connected;
            console.log('here conect to app');
            document.getElementById('disconnect').disabled = !connected;
            document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
            document.getElementById('response').innerHTML = '';
        }*/

        function connect() {
            var socket = new SockJS('/connect');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function(frame) {
               // setConnected(true);
                console.log('Connected:client ' + frame);
                stompClient.subscribe('/topic/connected', function(greeting){
                //    showGreeting(JSON.parse(greeting.body).content);
                	 console.log('create message');
                });
                
            });
        }

       

  /*      function sendName() {
            var name = document.getElementById('userNameInput').value;
            stompClient.send("/app/connected", {}, JSON.stringify({ 'sender': name }));
        }

        function showGreeting(message) {
            var response = document.getElementById('response');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.appendChild(document.createTextNode(message));
            response.appendChild(p);
        }
        
        
        function disconnect() {
            stompClient.disconnect();
            setConnected(false);
            console.log("Disconnected");
        }*/