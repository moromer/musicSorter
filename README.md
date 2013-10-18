== Notes ==
the musicSorter is currently running under 64Bit JVM and 64Bit SWT.

== How to integrate into Eclipse ==

1. Download swt 64bit project
	http://archive.eclipse.org/eclipse/downloads/drops/R-3.6.1-201009090800/download.php?dropFile=swt-3.6.1-win32-win32-x86_64.zip
2. Add the existing project (not unpacked) to your workspace. (http://help.eclipse.org/indigo/index.jsp?topic=/org.eclipse.platform.doc.isv/samples/org.eclipse.swt.examples/doc-html/swt_manual_setup.html)
3. Get the Code from GitHub
4. Add org.eclipse.swt to your project. Properties->Java Build Path -> Projects -> Add

== How to develeop ==

For each new implementation we are using a new feature branch e.g. "feature_mark_red" 
All Changes will be commit there. 
For each Release we have a new Release which includes all changes from the corresponding Feature branchesTag. 
This Release Tag will be committed (after successful tests) into the master
Only Release Tags can be put into the master. 
NO manually code changes (push) into the master is allowed
