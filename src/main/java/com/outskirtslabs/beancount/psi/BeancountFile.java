package com.outskirtslabs.beancount.psi;

import com.google.common.base.Stopwatch;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.outskirtslabs.beancount.BeancountFileType;
import com.outskirtslabs.beancount.BeancountLanguage;
import com.outskirtslabs.beancount.psi.stub.index.AccountStubIndex;
import com.outskirtslabs.beancount.psi.stub.index.CurrencySymbolStubIndex;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Slf4j
public class BeancountFile extends PsiFileBase {
    public BeancountFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, BeancountLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return BeancountFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Beancount File";
    }

    @Deprecated
    public Stream<String> getAllAccountsStrings() {
        var names = new HashSet<String>();
        this.acceptChildren(new BeancountRecursiveVisitor() {
            @Override
            public void visitAccountSymbol(@NotNull final BeancountAccountSymbol o) {
                names.add(o.getText());
            }
        });
        return names.stream();
    }

    public Stream<String> getAllAccountsCached() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Stream<String> distinct = AccountStubIndex.findAllAccounts(this.getProject()).stream()
                .distinct();
        log.info("getAllAccountsCached complete in {}", stopwatch.elapsed(TimeUnit.MICROSECONDS));
        return distinct;
    }
    
    public Stream<String> getAllCurrenciesCached() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Stream<String> distinct = CurrencySymbolStubIndex.findAllCurrencySymbols(this.getProject())
                .stream()
                .distinct();
        log.info("getAllAccountsCached complete in {}", stopwatch.elapsed(TimeUnit.MICROSECONDS));
        return distinct;
    }
}
