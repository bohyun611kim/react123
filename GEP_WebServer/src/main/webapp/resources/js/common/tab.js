 
$(document).ready(function() {
    $("#tab-group-1").tabs();
    $('#tab-group-deposit').tabs();

    $("#datepicker").datepicker({
        inline: true
    });
});
function openNavMobile(){
    document.getElementById("m_nav").style.right = "0%";
 }
 function closeNavMobile(){
    document.getElementById("m_nav").style.right = "-100%";
 }