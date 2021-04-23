import speech_recognition as sr
 
def speech_to_text(audio):
    r = sr.Recognizer()

    t = sr.AudioFile(audio)

    with t as source:
        res = r.record(source)
         
    text = r.recognize_google(res) 
    return(text)

if __name__ == '__main__':
    print(speech_to_text('audio.wav'))