from flask import Flask

# print a nice greeting.
def say_hello(username = "123456"):
    return '<p>WordCloud for %s!</p>\n' % username

# some bits of text for the page.
header_text = '''
    <html>\n<head> <title>'Welcome to WordCloud by ShapingData. A fun way to find where to eat!'</title> </head>\n<body>'''
instructions = '''
    <p><em>Hint</em>: This is a RESTful web service! Append a Zomato restaurant ID
    to the URL (for example: <code>/1234567</code>) to get the wordcloud for the restaurant.</p>\n'''
home_link = '<p><a href="/">Back</a></p>\n'
footer_text = '</body>\n</html>'

# EB looks for an 'application' callable by default.
application = Flask(__name__)

# add a rule for the index page.
application.add_url_rule('/', 'index', (lambda: header_text +
                                                say_hello() + instructions + footer_text))

# add a rule when the page is accessed with a name appended to the site
# URL.
application.add_url_rule('/<username>', 'hello', (lambda username:
                                                  header_text + say_hello(username) + home_link + footer_text))

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
