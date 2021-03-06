import argparse, collections, fnmatch, json, math, mysql.connector as sql, os, requests, sys, time
from ConfigParser import SafeConfigParser
from pytagcloud import create_tag_image, make_tags
from pytagcloud.lang.counter import get_tag_counts
import webbrowser
from pytagcloud.colors import COLOR_SCHEMES


def convert(input):
    if isinstance(input, dict):
        return {convert(key): convert(value) for key, value in input.iteritems()}
    elif isinstance(input, list):
        return [convert(element) for element in input]
    elif isinstance(input, unicode):
        return input.encode('utf-8')
    else:
        return input

config = SafeConfigParser()
script_dir = os.path.dirname(__file__)
config_file = os.path.join(script_dir, 'config/settings.cfg')
config.read(config_file)
sections = config.sections();
dir = config.get('files','outfolder')

for file in os.listdir(dir):
    f = open(dir+'/'+file, 'r')
    print ("Working on %s " % f)
    all_reviews = collections.deque()
    try:
        all_reviews = convert(json.loads(f.read()))
        f.close()
    except ValueError as err:
        print("%s in %s" % (err, file))

    total_results = 0

    count = 1
    total = len(all_reviews)
    print (total)

tags = make_tags(get_tag_counts(all_reviews), maxsize=50, colors= COLOR_SCHEMES['goldfish'])
create_tag_image(tags, 'cloud_large.png', size=(900, 600), fontname='Lobster')
webbrowser.open('cloud_large.png') # see results
