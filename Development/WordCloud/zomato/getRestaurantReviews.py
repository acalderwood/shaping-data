import requests
import io
import json
from pytagcloud import create_tag_image, make_tags
from pytagcloud.lang.counter import get_tag_counts
import webbrowser
class ShapingData:

    def reviews(this, id):
        all_reviews = ''
        api_key = 'db837d5e88fefd82d146b8e2e4e45c35'
        headers = {'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*', 'user-key': api_key}
        url = "https://developers.zomato.com/api/v2.1/reviews?res_id=%s" % (id)
        try:
            response = requests.get(url, headers=headers)
        except:
            print 'Network Issues!'
            return
        if response.status_code == 200:
            data = response.json()
            count = data["reviews_count"]
            if count == 0:
                print 'No Reviews!'
            else:
                for review in data["user_reviews"]:
                    review = review["review"]
                    print review["rating"]
                    print review["review_text"]
                    print "Posted: ",
                    print review["review_time_friendly"]
                    print "--------------"
                    all_reviews = all_reviews + review["review_text"] + ' '
                    out = "C:\PROJECTS\shaping-data\Development\WordCloud\zomato\out\\"
                    outfilename = ".".join(["vidhika",'json'])
                    outfilename = "".join([out,outfilename])
                    outfile = io.open(outfilename, mode='wt', encoding='utf8')
                    if all_reviews: # we got reviews, lets dump 'em to JSON
                        outfile.write(json.dumps(all_reviews,ensure_ascii=False, encoding='utf8'))
                    outfile.write(u'\n')
                    outfile.flush()
                    outfile.close() # clean up

        else:
            print 'Api Issues'

        print "All reviews word cloud words:       " + all_reviews

ShapingData().reviews(16746952)
#https://developers.zomato.com/api/v2.1/locations?query=Delray%20Beach                      116899
#https://developers.zomato.com/api/v2.1/search?entity_id=116899&entity_type=subzone         16926169
#https://developers.zomato.com/api/v2.1/reviews?res_id=16926169
