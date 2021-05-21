import speech_recognition as sr
import urllib.request
import numpy as np
import requests
 
def speech_to_text(url):
    urllib.request.urlretrieve(url, 'temp.wav')
    audio = open('temp.wav', 'rb')
      
    r = sr.Recognizer()

    t = sr.AudioFile(audio)

    with t as source:
        res = r.record(source)
         
    text = r.recognize_google(res) 
    return(text)

if __name__ == '__main__':
    url = 'https://www.signalogic.com/melp/EngSamples/Orig/male.wav'
    print(speech_to_text(url))