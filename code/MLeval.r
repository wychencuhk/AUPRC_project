library(MLeval)

# Illustrative example 1 with no ties
label <- c('P', 'N', 'P', 'N', 'N')
score <- c(0.9, 0.8, 0.7, 0.6, 0.5)

preds <- data.frame(N=c(1-score),P=score,obs=label,Group=rep('test',length(label)))

# Compute AUPRC and construct PRC
test2 <- evalm(preds,plots='r',rlinethick=0.8,fsize=8,bins=8)
test2$proc
