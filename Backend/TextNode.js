var http = require('http');
var url = require('url');
var formidable = require('formidable');
var fs = require('fs');
  
var spawn = require('child_process').spawn;
var doc = undefined;
 
// Image to Text
// ______________________________________________________
var temp1 = '';  
 
function extract_from_image(filename){
  var py    = spawn('python', ['Extract.py']);
  py.stdout.on('data', function(data){
    temp1 += data.toString();
    
  });
  
  py.stdout.on('end', function(){
      console.log(temp1);
  });
  
  console.log('omkkkkk');
  py.stdin.write(filename);
  py.stdin.end();
  
}
// ______________________________________________________

   
var http = require('http');

http.createServer(function (req, res) {
    if (req.url == '/imagetotextfile') {
        var form = new formidable.IncomingForm();
        form.parse(req, function (err, fields, files) {
          res.write('File uploaded\n');
          var temp = files.imagetoextract.path;
          var filepath = './' + files.imagetoextract.name;
          res.write(filepath+'\n');
            
          fs.rename(temp, filepath, function (err) {
            if (err) throw err;
              
          });
 
          extract_from_image(filepath); 

          res.end();

        });
      } 
      
      else if (req.url=='/translatetext'){
        //res.write(texttotranslate);
        console.log(texttotranslate);
        res.end();
 
      }
      
      
      else {
        res.writeHead(200, {'Content-Type': 'text/html'});

        // image to text
        res.write('<form action="imagetotextfile" method="post" enctype="multipart/form-data">');
        res.write('<input type="file" name="imagetoextract"><br> \
          <input type="submit">  \
        </form>');

        //translate from text
        res.write('<form action="translatetext" method="get" enctype="multipart/form-data">');
        res.write('<input type="text" name="texttotranslate"><br> \
          <input type="submit">  \
        </form>');
        
        console.log('meooo');
        res.end();
      }
}).listen(8080);