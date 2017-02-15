Setup Python

pip install virtualenv
pip install awsebcli
pip install flask=0.12

cd WordCloudPythonFlask

virtualenv eb-virt
eb-virt\Scripts\activate
(eb-virt)~$ pip install flask==0.12

http://docs.aws.amazon.com/elasticbeanstalk/latest/dg/create-deploy-python-flask.html

Install SSH from https://sourceforge.net/projects/sshwindows/files/OpenSSH%20for%20Windows%20-%20Release/
http://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-key-pairs.html#having-ec2-create-your-key-pair