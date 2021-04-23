try:
    import pytesseract 
    import cv2, sys

except Exception as e:
        print(e)

def extract_from_image(): 
    #imname = '/Users/tithighosh/Documents/LionCat/Screenshot 2021-04-20 at 3.30.19 PM.png'
    try:
        imname = sys.stdin.readlines()[0];
         
        img = cv2.imread(imname)
        #print(img)
        img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
        res = pytesseract.image_to_string(img)
        print(res)

    except Exception as e:
        print(e)


if __name__ == '__main__':
    extract_from_image()
