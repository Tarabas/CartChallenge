This Challenge is based on the following Kata:

http://codekata.com/kata/kata09-back-to-the-checkout/

The Structure has been implemented in a way to allow several different Rules to be applied for one
SKU. Normally you would also add priorization and Sorting for Priority but I just wanted to show that
the Structure is flexible enough so the Sorting of multiple Rules is just made with a LinkedHashSet.

Also the original Unit tests have additionally been split up into smaller tests because it it not a
good unit test style to test everything in one Test-Method. The original method has been used also for
confirmation.

Clean Code principles have been applied for easy maintenance and readability.