from Translate import *
from Extract import *
from STT import *
from flask import Flask, render_template, request
  
app = Flask(__name__)
  
# from website
"""@app.route("/")
def index():
    #global countries
    #countries = getCountries()
    return render_template("base.html")"""

# from website
"""@app.route("/", methods=["POST", "GET"])
def my_form_post():
    file = request.form["filetoextract"]
    return str(file)"""
 
#API_translate_from_text

@app.route("/translate_from_text")
def api_translate_from_text():
    frm_lang = request.args.get('from')
    to_lang = request.args.get('to')
    text = request.args.get('text')

    translated_text = translate_from_text(text, frm_lang, to_lang)
      
    return translated_text  

@app.route("/extract")
def api_extract():
    image_url = request.args.get('image')

    extracted_text = extract_from_image(image_url)
    print(extracted_text)
      
    return extracted_text 

@app.route("/translate_from_image")
def api_translate_from_image():
    frm_lang = request.args.get('from')
    to_lang = request.args.get('to')
    image_url = request.args.get('image')

    translated_text = translate_from_image(image_url, frm_lang, to_lang)
      
    return translated_text  

@app.route("/STT")
def api_STT():
    audio = request.args.get('audio') 
    extracted_text = speech_to_text(audio)
      
    return extracted_text
       
  
if __name__ == "__main__":
    app.run(debug=True)
 