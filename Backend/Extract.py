import pytesseract 
import cv2, sys
import numpy as np
import urllib.request
 

def extract_from_image(image_url): 
    try:
         
        req = urllib.request.urlopen(image_url)
        arr = np.asarray(bytearray(req.read()), dtype=np.uint8)
        
        img = cv2.imdecode(arr, -1)
        #print(img)
        img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
        res = pytesseract.image_to_string(img)
        print(res)

    except Exception as e:
        res=str(e)
        print(e)

    return res


if __name__ == '__main__':
    pass
    #extract_from_image('https://i.stack.imgur.com/WEMB9.png')
    
