import requests

from pytagcloud import create_tag_image, make_tags
from pytagcloud.colors import COLOR_SCHEMES
from pytagcloud.lang.counter import get_tag_counts


def create_file(res_id):
    all_reviews = ''
    api_key = 'db837d5e88fefd82d146b8e2e4e45c35'
    headers = {'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*', 'user-key': api_key}
    url = "https://developers.zomato.com/api/v2.1/reviews?res_id=%s" % (res_id)
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
                all_reviews = all_reviews + review["review_text"] + ' '

        all_reviews = convert(all_reviews)
        tags = make_tags(get_tag_counts(all_reviews), maxsize=50, colors= COLOR_SCHEMES['goldfish'])
        create_tag_image(tags, 'static/img/' + res_id + '.png', size=(900, 600), fontname='Lobster')
    else:
        print 'Api Issues'


def get_cloud(res_id):
    create_file(res_id)
    return '<img src="static/img/' + res_id + '.png"/>'


def get_base64(res_id):
    create_file(res_id)
    with open('static/img/' + res_id + '.png', 'rb') as f:
        data = f.read()
    return data.encode("base64")


def convert(input):
    if isinstance(input, dict):
        return {convert(key): convert(value) for key, value in input.iteritems()}
    elif isinstance(input, list):
        return [convert(element) for element in input]
    elif isinstance(input, unicode):
        return input.encode('utf-8')
    else:
        return input


from flask import Flask

# EB looks for an 'application' callable by default.
application = Flask(__name__)

# add a rule for the index page.
application.add_url_rule('/', 'index', (lambda: 'Please append a res_id to the URL e.g. /123456'))

# add a rule when the page is accessed with a res_id appended to the site
# URL.
application.add_url_rule('/<res_id>', 'wordcloud', (lambda res_id: get_cloud(res_id)))

application.add_url_rule('/b64/<res_id>', 'wordcloud-base64', (lambda res_id: get_base64(res_id)))

# run the app.
if __name__ == "__main__":
    # Setting debug to True enables debug output. This line should be
    # removed before deploying a production app.
    application.debug = True
    application.run()

from flask import Flask

app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'Welcome to WordCloud by ShapingData. A fun way to find where to eat!'

if __name__ == '__main__':
    app.run()
