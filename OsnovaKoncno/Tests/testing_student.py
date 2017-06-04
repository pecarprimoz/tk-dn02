from logging import exception

import pexpect

def studenti_add_works():
    baza = pexpect.pexpect()

    try:
        baza.expect("command>")
        baza.send("add")
        baza.expect("add> Student ID: ")
        baza.send("1")
        baza.expect("add> First name: ")
        baza.send("Janez")
        baza.expect("add> Last name: ")
        baza.send("Homan")
        baza.expect("add> Avg. grade: ")
        baza.send("3.5")
        baza.expect("OK")



        print "PASSED\ttest_studenti_add"
        pass

    except:
        print "FAILED\ttest_studenti_add"

    finally:
        baza.kill()

def studenti_add_wrong_data():
    baza = pexpect.pexpect()

    try:
        #naroben id
        baza.expect("command>")
        baza.send("add")
        baza.expect("add> Student ID: ")
        baza.send("this is not a number")
        baza.expect("Invalid input data")

        #prevelika sifra
        baza.expect("command>")
        baza.send("add")
        baza.expect("add> Student ID: ")
        baza.send("666420123333")
        baza.expect("Invalid input data")

        # naroben ime
        baza.expect("command>")
        baza.send("add")
        baza.expect("add> Student ID: ")
        baza.send("123")
        baza.expect("add> First name: ")
        baza.send("420=#!)(=#!)IDJ!RUJ")
        baza.expect("Invalid input data")

        #naroben priimek
        baza.expect("command>")
        baza.send("add")
        baza.expect("add> Student ID: ")
        baza.send("1")
        baza.expect("add> First name: ")
        baza.send("Janez")
        baza.expect("add> Last name: ")
        baza.send("243ff24f42f!.,2f44f22f43")
        baza.expect("Invalid input data")

        #narobna ocena
        baza.expect("command>")
        baza.send("add")
        baza.expect("add> Student ID: ")
        baza.send("1")
        baza.expect("add> First name: ")
        baza.send("Janez")
        baza.expect("add> Last name: ")
        baza.send("Homan")
        baza.expect("add> Avg. grade: ")
        baza.send("420.69")
        baza.expect("Invalid input data")

        # posebna sifra works
        baza.expect("command>")
        baza.send("add")
        baza.expect("add> Student ID: ")
        baza.send("1")
        baza.expect("add> First name: ")
        baza.send("Janez")
        baza.expect("add> Last name: ")
        baza.send("Homan")
        baza.expect("add> Avg. grade: ")
        baza.send("10.0")
        baza.expect("OK")

        print "PASSED\ttest_studenti_add_wrong_data"
        pass

    except:
        print "FAILED\ttest_studenti_add_wrong_data"

    finally:
        baza.kill()

def studenti_posebnosti():
    baza = pexpect.pexpect()

    try:

        baza.expect("command>")
        baza.send("Meep moop")
        baza.expect("Invalid command.")

        baza.expect("command>")
        baza.send("add 420a")
        baza.expect("Invalid command.")

        baza.expect("command>")
        baza.send("exit")
        baza.expect("Goodbye.")



        print "PASSED\ttest_posebnosti"
        pass

    except:
        print "FAILED\ttest_posebnosti"

    finally:
        baza.kill()

def studenti_remove():
    baza = pexpect.pexpect()

    try:
        #dodamo studenta
        baza.expect("command>")
        baza.send("add")
        baza.expect("add> Student ID: ")
        baza.send("1")
        baza.expect("add> First name: ")
        baza.send("Janez")
        baza.expect("add> Last name: ")
        baza.send("Homan")
        baza.expect("add> Avg. grade: ")
        baza.send("3.5")
        baza.expect("OK")

        #ga pobrisemo, so testi tudi v javi za to
        baza.expect("command>")
        baza.send("remove 1")
        baza.expect("OK")

        # dodamo studenta
        baza.expect("command>")
        baza.send("add")
        baza.expect("add> Student ID: ")
        baza.send("1")
        baza.expect("add> First name: ")
        baza.send("Janez")
        baza.expect("add> Last name: ")
        baza.send("Homan")
        baza.expect("add> Avg. grade: ")
        baza.send("3.5")
        baza.expect("OK")

        #pobrisemo neveljavno
        baza.expect("command>")
        baza.send("remove 2")
        baza.expect("Error: Student does not exist")

        #nimamo pravih ukazov
        baza.expect("command>")
        baza.send("remove himom")
        baza.expect("Invalid input data.")

        ## TESTIRANJE SE DRUGEGA NACINA ##
        baza.expect("command>")
        baza.send("remove")
        baza.expect("remove> First name: ")
        baza.send("Janez")
        baza.expect("remove> Last name: ")
        baza.send("Homan")
        baza.expect("OK")

        baza.expect("command>")
        baza.send("add")
        baza.expect("add> Student ID: ")
        baza.send("1")
        baza.expect("add> First name: ")
        baza.send("Janez")
        baza.expect("add> Last name: ")
        baza.send("Homan")
        baza.expect("add> Avg. grade: ")
        baza.send("3.5")
        baza.expect("OK")

        baza.expect("command>")
        baza.send("remove")
        baza.expect("remove> First name: ")
        baza.send("df321f13f13")
        baza.expect("Invalid input data.")

        baza.expect("command>")
        baza.send("remove")
        baza.expect("remove> First name: ")
        baza.send("Janez")
        baza.expect("remove> Last name: ")
        baza.send("f32qf24f2f24f")
        baza.expect("Invalid input data.")

        print "PASSED\ttest_studenti_remove"
        pass

    except:
        print "FAILED\ttest_studenti_remove"

    finally:
        baza.kill()

def studenti_search():
    baza = pexpect.pexpect()

    try:
        baza.expect("command>")
        baza.send("add")
        baza.expect("add> Student ID: ")
        baza.send("1")
        baza.expect("add> First name: ")
        baza.send("Janez")
        baza.expect("add> Last name: ")
        baza.send("Homan")
        baza.expect("add> Avg. grade: ")
        baza.send("3.5")
        baza.expect("OK")

        baza.expect("command>")
        baza.send("search 1")
        baza.expect("1 | Homan, Janez | 3.5")

        baza.expect("command>")
        baza.send("search 2")
        baza.expect("Student does not exist.")

        baza.expect("command>")
        baza.send("search meep")
        baza.expect("Invalid input data.")

        baza.expect("command>")
        baza.send("search")
        baza.expect("search> First name: ")
        baza.send("Janez")
        baza.expect("search> Last name: ")
        baza.send("Homan")
        baza.expect("1 | Homan, Janez | 3.5")

        baza.expect("command>")
        baza.send("search")
        baza.expect("search> First name: ")
        baza.send("13rdf321f342r!")
        baza.expect("Invalid input data.")

        baza.expect("command>")
        baza.send("search")
        baza.expect("search> First name: ")
        baza.send("Janez")
        baza.expect("search> Last name: ")
        baza.send("f13f13f13#!")
        baza.expect("Invalid input data.")

        print "PASSED\ttest_studenti_search"
        pass

    except:
        print "FAILED\ttest_studenti_search"

    finally:
        baza.kill()

import os, stat
def studenti_save_restore():
    baza = pexpect.pexpect()

    try:

        baza.expect("command>")
        baza.send("add")
        baza.expect("add> Student ID: ")
        baza.send("1")
        baza.expect("add> First name: ")
        baza.send("Janez")
        baza.expect("add> Last name: ")
        baza.send("Homan")
        baza.expect("add> Avg. grade: ")
        baza.send("3.5")
        baza.expect("OK")


        baza.expect("command>")
        baza.send("save moj_janez_novo_file")
        baza.expect("OK")

        print "PASSED\ttest_save_restore"
        pass

    except:
        print "FAILED\ttest_save_restore"

    finally:
        baza.kill()

def studenti_Restore():
    baza = pexpect.pexpect()

    try:
        baza.expect("command>")
        baza.send("restore moj_janezXXX.bin")
        baza.expect("java.io.FileNotFoundException: moj_janezXXX.bin (The system cannot find the file specified)")
        baza.expect("OK")

        baza.expect("command>")
        baza.send("restore moj_janez_novo_file")
        baza.expect("OK")

        print "PASSED\ttest_restore"
        pass

    except:
        print "FAILED\ttest_restore"

    finally:
        baza.kill()

def studenti_print():
    baza = pexpect.pexpect()

    try:
        baza.expect("command>")
        baza.send("print")
        baza.expect(">> No. of students: 0")

        baza.expect("command>")
        baza.send("add")
        baza.expect("add> Student ID: ")
        baza.send("1")
        baza.expect("add> First name: ")
        baza.send("Janez")
        baza.expect("add> Last name: ")
        baza.send("Homan")
        baza.expect("add> Avg. grade: ")
        baza.send("3.5")
        baza.expect("OK")

        baza.expect("command>")
        baza.send("print")
        baza.expect(">> No. of students 1")
        baza.expect("	1 | Homan | Janez | 3.5")

        print "PASSED\ttest_print"
        pass
#
    except:
        print "FAILED\ttest_print"

    finally:
        baza.kill()

def studenti_count():
    baza = pexpect.pexpect()

    try:
        baza.expect("command>")
        baza.send("count")
        baza.expect(">> No. of students: 0")

        baza.expect("command>")
        baza.send("add")
        baza.expect("add> Student ID: ")
        baza.send("1")
        baza.expect("add> First name: ")
        baza.send("Janez")
        baza.expect("add> Last name: ")
        baza.send("Homan")
        baza.expect("add> Avg. grade: ")
        baza.send("3.5")
        baza.expect("OK")

        baza.expect("command>")
        baza.send("count")
        baza.expect(">> No. of students: 1")

        print "PASSED\ttest_count"
        pass

    except:
        print "FAILED\ttest_count"

    finally:
        baza.kill()

def studenti_reset():
    baza = pexpect.pexpect()

    try:
        baza.expect("command>")
        baza.send("add")
        baza.expect("add> Student ID: ")
        baza.send("1")
        baza.expect("add> First name: ")
        baza.send("Janez")
        baza.expect("add> Last name: ")
        baza.send("Homan")
        baza.expect("add> Avg. grade: ")
        baza.send("3.5")
        baza.expect("OK")

        baza.expect("command>")
        baza.send("count")
        baza.expect(">> No. of students: 1")

        baza.expect("command>")
        baza.send("reset")
        baza.expect("reset> Are you sure (y|n):")
        baza.send("y")
        baza.expect("OK")

        baza.expect("command>")
        baza.send("reset")
        baza.expect("reset> Are you sure (y|n):")
        baza.send("n")
        baza.expect("OK")


        print "PASSED\ttest_reset"
        pass

    except:
        print "FAILED\ttest_reset"

    finally:
        baza.kill()
studenti_add_works()
studenti_add_wrong_data()
studenti_remove()
studenti_search()
studenti_save_restore()
studenti_Restore()
studenti_print()
studenti_count()
studenti_reset()
studenti_posebnosti()