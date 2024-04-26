library(MLeval)

# Illustrative example 1 with no ties
label <- c(1, 0, 1, 0, 0)
score <- c(0.9, 0.8, 0.7, 0.6, 0.5)

label[label==1]<-'P'
label[label==0]<-'N'

preds <- data.frame(N=c(1-score),P=score,obs=label,Group=rep('test',length(label)))

# Compute AUPRC and construct PRC
test2 <- evalm(preds,plots='r',rlinethick=0.8,fsize=8,bins=8)
test2$proc
