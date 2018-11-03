/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $("#textField").keypress(function (e) {
        console.log(e.which);
        if (e.which === 13) {
            var postData = {
                screenName : $("#nameField").val(),
                message : $("#textField").val()
            };
            $.ajax({
                url: "/chat/api/chat",
                type: "POST",
                dataType: "json",
                data : JSON.stringify(postData),
                contentType : "application/json",
                success: function (e) {
                    $("#textField").val("");
                    console.log(e);
                },
                error: function(a,b,c){
                    console.log(a,b,c);
                }
            });
        }
    });
});

var source = new EventSource('/chat/api/chat/subscribe');

source.addEventListener('message', function (e) {
    console.log(e.data);
    var html = $("#scrollable-content").html();
    var parsed = JSON.parse(e.data);
    html = html + parsed.screenName + " : " + parsed.message + "\n";
    $("#scrollable-content").html(html);

}, false);

source.addEventListener('open', function (e) {
    console.log("Opened Connection");
}, false);

source.addEventListener('error', function (e) {
    if (e.readyState == EventSource.CLOSED) {
        // Connection was closed.
    }
}, false);



