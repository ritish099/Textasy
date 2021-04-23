from Extract import extract_from_image

import translators as ts  
  
def translate_from_image(imname, frm, to):
    text = extract_from_image(imname)
    print(text)
    res = translate_from_text(text, frm, to)
    return res


def translate_from_text(text, frm, to):
    res = ts.google(text, if_use_cn_host=True, \
    to_language=to, from_language=frm)
 
    return res 

if __name__ == '__main__':
    print(translate_from_image('image.png', 'en', 'pt'))
    #print(translate_from_text('não faça assim. eu coro',\
     #'pt', 'en'))
