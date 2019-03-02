

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
        console.log('error load chats'+dataJSON);
      }
    } 
  requestGET.send();






var searchInput = document.getElementById('search_input');
searchInput.addEventListener("input", searchNewConversation);


function searchNewConversation() {
	var searchList = document.getElementById('search_list');
	var contactList = document.getElementById('contacts_list');
	var searchInput = document.getElementById('search_input');
	
	if(searchInput.value.length === 0){
		searchList.hidden = true;
		contactList.hidden = false;
		searchList.innerHTML='';
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
		startConversationBtn.addEventListener("focus", addNewConversation);
	});
};

function addNewConversation(){
	var contactId = this.previousSibling.innerText;
	
	var requestPOST = new XMLHttpRequest();

	requestPOST.open("POST", 'http://localhost:8080/conversation', true);
	requestPOST.setRequestHeader("Content-Type", "application/json");
	requestPOST.onreadystatechange = function () {
	    if ( requestPOST.readyState === 4 && requestPOST.status === 200) {
	    	var dataJSON = JSON.parse(requestPOST.responseText);
	        console.log( this.responseText);
	        buildConversationList(dataJSON);
	    }else {
	      console.log('error send data on /conversation');
	    }
	    var searchInput = document.getElementById('search_input');
        searchInput.value="";
        
      var searchList = document.getElementById('search_list');
        searchList.hidden = true;
        var contactList = document.getElementById('contacts_list');
        contactList.hidden = false;
	};

	var data = JSON.stringify({
	  "id": contactId
	});
	requestPOST.send(data);
	};

function buildConversationList(conversation){
	var contactItem = document.createElement('div');
	contactItem.className="chat_list";
	contactItem.addEventListener("click", contactOnFocus);
	contactItem.innerHTML ="<div hidden id=\"conversation_id\">"+conversation.id+"</div>"
						  +"<div class=\"chat_people\">"
						  +"<div class=\"chat_img\">"
						  +"<img src=\"https://ptetutorials.com/images/user-profile.png\" alt=\"sunil\">"
						  +"</div>"
						  +"<div class=\"chat_ib\">"
						  +"<h5 >"+conversation.conversationName+"</h5>"
						  +"</div>  ";
	
	var contactList = document.getElementById('contacts_list');
	contactList.prepend(contactItem);
};

function contactOnFocus(){
	var iter = document.getElementsByClassName("chat_list active_chat").length;
	console.log(iter);
	if(iter > 0){
		iter--;
		var elem = document.getElementsByClassName("chat_list"+" active_chat")[i];
		elem.className =  "chat_list";
	}
	if(this.className ==="chat_list" ){
	this.className += " active_chat";
	} 

}
