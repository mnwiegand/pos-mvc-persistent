package com.example.posmvcpersistent.models.forms;


import com.example.posmvcpersistent.models.Category;
import com.example.posmvcpersistent.models.Vendor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PrepBatchForm {

    @NotNull
    private Vendor vendor;

    @NotNull
    @Range(min=1, max=100, message = "Enter an integer between 1 and 100")
    private int lineCount;

    @NotNull
    private int batchId;

    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }

    public PrepBatchForm(){}

    public PrepBatchForm(Vendor vendor, int lineCount, int batchId) {
        this.vendor = vendor;
        this.lineCount = lineCount;
        this.batchId = batchId;
    }
}
