
var requestGET = new XMLHttpRequest();

requestGET.open('GET', 'http://localhost:8080/conversation', true);
requestGET.onload = function () {
   var dataJSON = JSON.parse(this.responseText);
      if (requestGET.status >= 200 && requestGET.status < 400) {
    	 console.log(dataJSON.responseText);
    	 console.log('reload');
        dataJSON.forEach(conversation =>{
        buildConversationList(conversation);
        });
      }else {
        console.log('error load conversation');
      }
    } 
  requestGET.send();


var searchInput = document.getElementById('search_input');
searchInput.addEventListener("input", searchNewConversation);

// request to the server to search for users
function searchNewConversation() {
	var searchList = document.getElementById('search_list');
	var contactList = document.getElementById('contacts_list');
	var searchInput = document.getElementById('search_input');
	
	if(searchInput.value.length === 0){
		searchList.hidden = true;
		searchList.innerHTML='';
		contactList.hidden = false;
	}else{
		searchList.hidden = false;
		contactList.hidden = true;
		
	var requestPOST = new XMLHttpRequest();

	requestPOST.open("POST", 'http://localhost:8080/search', true);
	requestPOST.setRequestHeader("Content-Type", "application/json");
	requestPOST.onreadystatechange = function () {
	    if ( requestPOST.readyState === 4 && requestPOST.status === 200) {
	    	var dataJSON = JSON.parse(requestPOST.responseText);
	        console.log( this.responseText);
	        showSearchResult(dataJSON);
	    }else {
	      console.log('error send data');
	    }
	};

	var data = JSON.stringify({
	  "name": searchInput.value
	});
	requestPOST.send(data);
	}
};
// show serch result from DB
function showSearchResult(data){
	var searchList = document.getElementById('search_list');
	searchList.innerHTML='';
	data.forEach(contact => {
		
		var contactList = document.createElement('div');
		contactList.className="chat_list";
		contactList.innerHTML= "<div class=\"chat_people\">"
						+"<div class=\"chat_img\">"
						+"<img src=\"https://ptetutorials.com/images/user-profile.png\" alt=\"sunil\">"
						+"</div>  <div class=\"chat_ib\">"
						+"<h5>"+contact.name+"</h5>"
						+"<div hidden id=\"contact_id\">"+contact.id +" </div>"
						+"<button type=\"button\" id=\"create_conversation_"+contact.id+"\" class=\"btn-sm btn btn-outline-primary\">contact"
						+"</button>  </div> </div> "
		
		searchList.appendChild(contactList);
		
		var startConversationBtn = document.getElementById('create_conversation_'+contact.id);
		startConversationBtn.addEventListener("click", addNewConversation); 
		
	});
};

//create new conversation 
function addNewConversation(){
	var contactId = this.previousSibling.innerText;
	
	var requestPOST = new XMLHttpRequest();

	requestPOST.open("POST", 'http://localhost:8080/conversation', true);
	requestPOST.setRequestHeader("Content-Type", "application/json");
	requestPOST.onreadystatechange = function () {
	    if ( requestPOST.readyState === 4 && requestPOST.status === 200) {
	    	var dataJSON = JSON.parse(requestPOST.responseText);
	    	//add new conversation to list
	        buildConversationList(dataJSON);
	    }else {
	      console.log('error send data on /conversation');
	    }
	   document.getElementById('search_input').value="";
       document.getElementById('search_list').hidden = true;
       document.getElementById('contacts_list').hidden = false;
       
	};

	var data = JSON.stringify({
	  "id": contactId
	});
	requestPOST.send(data);
	};

	// add new conversation to contact list
function buildConversationList(conversation){
	var contactItem = document.createElement('div');
	contactItem.className="chat_list";
	contactItem.addEventListener("click", contactOnFocus);
	contactItem.innerHTML ="<div hidden id=\"conversation_"+conversation.id+"\">"+conversation.id+"</div>"
						  +"<div class=\"chat_people\">"
						  +"<div class=\"chat_img\">"
						  +"<img src=\"https://ptetutorials.com/images/user-profile.png\" alt=\"sunil\"> </div>"
						  +"<div class=\"chat_ib\">"
						  +"<h5>"+conversation.conversationName+"</h5>"
						  +"</div> ";
	
	var contactList = document.getElementById('contacts_list');
	contactList.prepend(contactItem);
		
	subscriptionToTheConversation(conversation.id);
};

// activate conversation on click
function contactOnFocus(){
	var iter = document.getElementsByClassName("chat_list active_chat").length;
	if(iter > 0){
		iter--;
		var elem = document.getElementsByClassName("chat_list active_chat")[iter];
		if(this != elem){
		elem.className = "chat_list";
		}
	} 
	if(this.className ==="chat_list" ){
	this.className += " active_chat";

	document.getElementById('chat_history').innerHTML='';
	 console.log(document.getElementsByClassName("chat_list active_chat")[0].children[0].innerText);
	loadLastMessage(document.getElementsByClassName("chat_list active_chat")[0].children[0].innerText);
	} 
};

	/** ******************************************************************************************* */
	/* Stomp js */

// create listener to send button
document.getElementById('message_send_button').addEventListener("click", function(){
	 var iter = document.getElementsByClassName("chat_list active_chat").length;
	 if(iter === 1){
		var conversationId = document.getElementsByClassName("chat_list active_chat")[0].firstChild.innerText;
		sendMessage(conversationId);
	 }
});

var stompClient = null;

function subscriptionToTheConversation( conversationId) {
	var socket = new SockJS('/message/'+conversationId);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected:client to  /topic/response/'+conversationId +"; status "+ frame);
        
        stompClient.subscribe('/topic/response/'+conversationId, function(message){
        var responce = JSON.parse(message.body);
        showMessage(JSON.parse(message.body)) ;
        
        });  
        
    });
};



function showMessage(data){
	var conversation = document.getElementById('conversation_'+data.conversationId);
	if(conversation === null){
		console.log("NEW CONTACT WRITE:"+data);
	}
	if(conversation.parentNode.className ==="chat_list active_chat" ){
		createMessages(data);
	}

}


//create dialog message
function createMessages(data){
	
	var message = document.createElement('div');
	if(data.sender === document.getElementById('userNameLable').textContent){
		message.className="outgoing_msg";
		message.innerHTML = "<div class=\"sent_msg\">"
							+"<p>"+data.text+"</p>"
							+"<span class=\"time_date\">"+ data.date +"</span>"
							+"</div>"
	}else{
		message.className="incoming_msg";
		message.innerHTML =  "<div class=\"incoming_msg_img\">"
						+"<img src=\"https://ptetutorials.com/images/user-profile.png\" alt=\"sunil\"> </div>"
						+"<div class=\"received_msg\">"
						+"<div class=\"received_withd_msg\">"
						+"<h5>"+data.sender+"<h5>"
						+"<p>"+data.text+"</p>"
						+"<span class=\"time_date\">"+ data.date +"</span>"
						+"</div> </div>"
	}
	document.getElementById("chat_history").appendChild(message);
}

// send message to listener
function sendMessage(conversationId) {
    var sender = document.getElementById('userNameLable').textContent;
    var text = document.getElementById('sendTextInput').value;
    var data = JSON.stringify({
    	'sender': sender ,
    	'text' : text ,
    	'conversationId' : conversationId
    }) ;
    
    document.getElementById('sendTextInput').value='';
    stompClient.send('/app/message/'+conversationId, {}, data);
   
};

function loadLastMessage(conversationId) {
	console.log('LOAD LAST MESSAGES');
	var requestPOST = new XMLHttpRequest();

	requestPOST.open("POST", 'http://localhost:8080/conversation/message', true);
	requestPOST.setRequestHeader("Content-Type", "application/json");
	requestPOST.onreadystatechange = function () {
	    if ( requestPOST.readyState === 4 && requestPOST.status === 200) {
	    	var dataJSON = JSON.parse(requestPOST.responseText);
	    	//add old message to conversation
	    	dataJSON.forEach(message=>{
	    		createMessages(message);
	    	});
	    	
	    }else {
	      console.log('error send data on /conversation');
	    }
       
	};

	var data = JSON.stringify({
	  "id": conversationId
	});
	requestPOST.send(data);
	};







