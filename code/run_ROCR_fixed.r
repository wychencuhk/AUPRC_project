# Illustrative example 1 with no ties
label <- c(1, 0, 1, 0, 0)
score <- c(0.9, 0.8, 0.7, 0.6, 0.5)

rocr_fixed <- function(predictions, labels){
  pos.label <- 1
  neg.label <- 0
  pred.order <- order(predictions, decreasing=TRUE)
  predictions.sorted <- predictions[pred.order]
  tp <- cumsum(labels[pred.order]==pos.label)
  fp <- cumsum(labels[pred.order]==neg.label)
  dups <- rev(duplicated(rev(predictions.sorted)))
  tp <- c(0, tp[!dups])
  fp <- c(0, fp[!dups])
  tmp <- aggregate(list(fp=fp), by=list(tp=tp), min)
  tp <- tmp$tp
  fp <- tmp$fp
  prec <- tp / (fp + tp)
  n.pos <- tp[length(tp)]
  rec <- tp / n.pos
  if (fp[1] == 0 & tp[1] == 0) {
    prec[1] = 1
  }
  finite.bool <- is.finite(prec) & is.finite(rec)
  prec <- prec[ finite.bool ]
  rec <- rec[ finite.bool ]	
  j <- 0
  for (i in seq_along(rec[-length(rec)])) {
    if (tp[i+1] - tp[i] > 2) {
      skew = (fp[i+1]-fp[i]) / (tp[i+1]-tp[i])
      x = seq(1, tp[i+1]-tp[i], by=1)
      rec <- append(rec, (x+tp[i])/n.pos, after=i+j)
      prec <- append(prec, (x+tp[i])/(tp[i]+fp[i]+x+ skew*x), after=i+j)
      j <- j + length(x)
    }
  }
  
  auc <- 0
  for (i in seq.int(from = 2, to = length(rec))) {
    auc <- auc + 0.5 * (rec[i] - rec[i-1]) * (prec[i] + prec[i-1])
  }
  return(auc)
}

rocr_fixed(predictions = score, labels = label)