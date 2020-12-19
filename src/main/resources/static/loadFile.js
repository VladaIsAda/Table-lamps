
  var loadFile = function(event) {
    var output = document.getElementById('output');
    output.style.visibility = "visible";
    output.src = URL.createObjectURL(event.target.files[0]);
  };