# variable $2 is the file to be transferred
# $1 is the user
if [ -d $DIRECTORY ]; then
	scp -r $2 $1@cmpt373-1177z.cmpt.sfu.ca:/var/www/cmpttest.com/public_html
fi

if [ ! -d $DIRECTORY ]; then
	scp $2 $1@cmpt373-1177z.cmpt.sfu.ca:/var/www/cmpttest.com/public_html
fi