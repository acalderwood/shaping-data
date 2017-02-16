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

On server
http://www.pygame.org/wiki/CompileRedHat







Installing pygame

[ec2-user@ip-172-31-9-103 ~]$ history
    1  yum install pygame
    2  sudo
    3  sudo yum install pygame
    4  sudo yum install python-pygame
    5  sudo yum install python-game
    6  wget http://www.pygame.org/ftp/pygame-1.8.1release.tar.gz
    7  tar xvf pygame-1.8.1release.tar.gz
    8  cd pygame-1.8.1release
    9  su
   10  sudo yum install smpeg-devel SDL_ttf-devel SDL_image-devel SDL_mixer-devel libpng-devel libjpeg-devel python-devel
   11  python setup.py install
   12  yum install smpeg-config
   13  sudo yum install smpeg-config
   14  sudo yum install sdl-config
   15  ls
   16  cd pygame-1.8.1release
   17  ls
   18  python setup.py install
   19  sudo python setup.py install
   20  python config.py
   21  yum install sdl
   22  sudo yum install sdl
   23  sudo yum install SDL*
   24  sudo yum install SDL
   25  sudo yum install *SDL*
   26  yum list *SDL*
   27  wget http://dl.fedoraproject.org/pub/epel/7/x86_64/e/epel-release-7-9.noarch.rpm
   28  rpm -ivh epel-release-7-9.noarch.rpm
   29  sudo rm /var/lib/rpm/.rpm.lock
   30  rpm -ivh epel-release-7-9.noarch.rpm
   31  touch  /var/lib/rpm/.rpm.lock
   32  sudo touch  /var/lib/rpm/.rpm.lock
   33  sudo rpm -ivh epel-release-7-9.noarch.rpm
   34  sudo rpm -ivh epel-release-6-8-9.noarch.rpm
   35  wget http://dl.fedoraproject.org/pub/epel/7/x86_64/e/epel-release-6-8-9.noarch.rpm
   36  wget http://download.fedoraproject.org/pub/epel/6/i386/epel-release-6-8.noarch.rpm
   37  rpm -ivh epel-release-6-8.noarch.rpm
   38  sudo rpm -ivh epel-release-6-8.noarch.rpm
   39  yum list installed
   40  yum install python-devel SDL_image-devel SDL_mixer-devel SDL_ttf-devel SDL-devel smpeg-devel numpy subversion portmidi-devel
   41  sudo yum install python-devel SDL_image-devel SDL_mixer-devel SDL_ttf-devel SDL-devel smpeg-devel numpy subversion portmidi-devel
   42  hg clone https://bitbucket.org/pygame/pygame
   43  sudo yum install hg
   44  hg clone https://bitbucket.org/pygame/pygame
   45  cd pygame
   46  python setup.py build
   47  sudo python setup.py install
   48  python setup.py build
   49  sudo yum install SDL_image-devel
   50  rpm -i https://kojipkgs.fedoraproject.org//packages/pygame/1.9.1/4.el6/x86_64/pygame-1.9.1-4.el6.x86_64.rpm
   51  rpm -i ftp://rpmfind.net/linux/fedora/linux/releases/25/Everything/x86_64/os/Packages/s/SDL-1.2.15-21.fc24.x86_64.rpm
   52  sudo rpm -i ftp://rpmfind.net/linux/fedora/linux/releases/25/Everything/x86_64/os/Packages/s/SDL-1.2.15-21.fc24.x86_64.rpm
   53  sudo rpm -i https://kojipkgs.fedoraproject.org//packages/pygame/1.9.1/4.el6/x86_64/pygame-1.9.1-4.el6.x86_64.rpm
   54  sudo rpm -i ftp://rpmfind.net/linux/fedora/linux/releases/25/Everything/x86_64/os/Packages/s/SDL_image-1.2.12-14.fc24.x86_64.rpm
   55  sudo rpm -i ftp://rpmfind.net/linux/fedora/linux/updates/25/x86_64/l/libpng-1.6.27-1.fc25.x86_64.rpm
   56  pip
   57  pip install pygame
   58  pip install sdl-config
   59   freetype-config: command not found
   60  cd pygame-1.8.1release
   61  sudo python setup.py build
   62  sudo rpm -i ftp://rpmfind.net/linux/fedora/linux/releases/25/Everything/x86_64/os/Packages/s/SDL-devel-1.2.15-21.fc24.i686.rpm
   63  yum install SDL-devel
   64  sudo yum install SDL-devel
   65  cd ..
   66  wget http://www.libsdl.org/release/SDL-1.2.14.tar.gz
   67  tar -zxvf SDL-1.2.14.tar.gz
   68  cd SDL-1.2.14
   69  ./configure
   70  sudo make all
   71  cd ../
   72  cd pygame-1.8.1release
   73  sudo python setup.py build
   74  cd ..
   75  cd SDL-1.2.14
   76  ls
   77  cd build
   78  ls
   79  cd ..
   80  sudo make install
   81  cd ..
   82  ls
   83  cd pygame-1.8.1release
   84  sudo python setup.py build
   85  sdl-config
   86  python setup.py build
   87  python setup.py install
   88  ls
   89  pip install pygame
   90  yum install freetype
   91  sudo yum install freetype
   92  pip install pygame
   93  sudo yum install freetype-config
   94  sudo yum install freetype-devel
   95  pip install pygame
   96  sudo pip install pygame
   97  sudo python setup.py build
   98  sudo rpm -i ftp://rpmfind.net/linux/fedora/linux/releases/25/Everything/x86_64/os/Packages/l/libX11-devel-1.6.4-1.fc25.x86_64.rpm
   99  sudo yum install libX11
  100  sudo yum install libX11-devel
  101  sudo python setup.py build
  102  sudo yum install libX11-devel
  103  sudo python setup.py build
  104  sudo pip install pygame
  105  sudo sdl-config
  106  sdl-config
  107  sudo pip install pygame
  108  pip install pygame
  109  ls /tmp/
  110  ls
  111  ls -ltr
  112  cd build/
  113  ls
  114  cd ..
  115  pip install pygame
  116  Command "python setup.py egg_info" failed with error code 1 in
  117  cd ..
  118  wget https://pypi.python.org/packages/dc/76/fdfb5cee3432192a89df6aad8a33eed96ba6a3a16e0dca951db9fef03688/pygame-1.9.3-cp27-cp27mu-manylinux1_x86_64.whl
  119  pip install pygame-1.9.3-cp27-cp27mu-manylinux1_x86_64.whl
  120  pip install --upgrade pip
  121  sudo pip install --upgrade pip
  122  sudo pip install pygame-1.9.3-cp27-cp27mu-manylinux1_x86_64.whl
  123  pip install pygame-1.9.3-cp27-cp27mu-manylinux1_x86_64.whl
  124  pip
  125  pip2
  126  pip2 install pygame-1.9.3-cp27-cp27mu-manylinux1_x86_64.whl
  127  sudo pip2 install pygame-1.9.3-cp27-cp27mu-manylinux1_x86_64.whl
  128  pip
  129  which pip
  130   /usr/bin/pip: No such file or directory
  131  exit
  132  pip
  133  sudo pip install pygame-1.9.3-cp27-cp27mu-manylinux1_x86_64.whl
  134  pip install pygame-1.9.3-cp27-cp27mu-manylinux1_x86_64.whl
  135  sudo /usr/local/bin/pip install pygame-1.9.3-cp27-cp27mu-manylinux1_x86_64.whl
  136  pygame
  137  history
