   var stompClient = null;
 
        function connect() {
            var socket = new SockJS('/newMessage');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function(frame) {
                stompClient.subscribe('/topic/newMessage', function(message){
                    refreshMessages(JSON.parse(JSON.parse(message.body).content));
                });
            });
        }
 
        function disconnect() {
            if (stompClient != null) {
                stompClient.disconnect();
            }
        }
 
        function refreshMessages(messages) {
            $(".media-list").html("");
            $.each(messages.reverse(), function(i, message) {
                $(".media-list").append('<li class="media"><div class="media-body"><div class="media"><div class="media-body">'
                + message.text + '<br/><small class="text-muted">' + message.author + ' | ' + new Date(message.createDate) + '</small><hr/></div></div></div></li>');
            });
        }
 
       /* $(function(){
 
            if (typeof $.cookie("realtime-chat-nickname") === 'undefined') {
                window.location = "/login"
            } else {
                connect();
                $.get("/messages", function (messages) {
                    refreshMessages(messages)
                });
 
                $("#sendMessage").on("click", function() {
                    sendMessage()
                });
 
                $('#messageText').keyup(function(e){
                    if(e.keyCode == 13)
                    {
                        sendMessage();
                    }
                });
            }*/
 
            function sendMessage() {
                $container = $('.media-list');
                $container[0].scrollTop = $container[0].scrollHeight;
                var message = $("#messageText").val();
                var author = $.cookie("realtime-chat-nickname");
 
                stompClient.send("/app/newMessage", {}, JSON.stringify({ 'text': message, 'author': author}));
 
                $("#messageText").val("")
                $container.animate({ scrollTop: $container[0].scrollHeight }, "slow");
 
            }
        })