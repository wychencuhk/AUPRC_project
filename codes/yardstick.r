library(dplyr)
library(yardstick)

# Illustrative example 1 with no ties
score <- c(1, 0, 1, 0, 0)
label <- c(0.9, 0.8, 0.7, 0.6, 0.5)

label <- factor(label)
data <- data.frame(score,label)

# Direct straight line
# Compute AUPRC
print(data %>% pr_auc(label,score,event_level = "second"))
# Construct PRC
data %>% pr_curve(label,score,event_level = "second") %>% autoplot()

# AP
print(data %>% average_precision(label,score,event_level = "second"))
# No visualization