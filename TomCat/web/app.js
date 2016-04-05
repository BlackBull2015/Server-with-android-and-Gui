function readfile(){

jQuery.get('test.txt', function(txt) {
// alert(txt);
//process text file line by line
$('#output').text(txt);
});

}