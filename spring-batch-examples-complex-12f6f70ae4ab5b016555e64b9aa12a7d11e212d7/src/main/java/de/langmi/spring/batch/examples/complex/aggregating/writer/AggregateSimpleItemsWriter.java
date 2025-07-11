/**
 * Copyright 2012 Michael R. Lange <michael.r.lange@langmi.de>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.langmi.spring.batch.examples.complex.aggregating.writer;

import de.langmi.spring.batch.examples.complex.aggregating.AggregatedItem;
import de.langmi.spring.batch.examples.complex.support.SimpleItem;
import java.util.Arrays;
import java.util.List;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;

/**
 * Itemwriter which aggregates items before delegating to another wrapped itemwriter.
 * Actual aggregation is controlled by an shared id of the used items.
 * Works only if the items come ordered in sets with same shared id.
 * 
 * @author Michael R. Lange <michael.r.lange@langmi.de>
 * @see http://stackoverflow.com/questions/8837487/how-to-process-logically-related-rows-after-itemreader-in-springbatch
 */
public class AggregateSimpleItemsWriter implements ItemWriter<SimpleItem>, StepExecutionListener {

    private ItemWriter<AggregatedItem> delegate;
    private AggregatedItem tempData = null;
    private StepExecution stepExecution;
    private static final String KEY_READER_EXHAUSTED = "readerExhausted";

    @Override
    public void write(List<? extends SimpleItem> items) throws Exception {

        // setup with first sharedId at startup
        if (tempData == null) {
            tempData = new AggregatedItem(items.get(0).getSharedId());
        }

        for (SimpleItem item : items) {
            // either actual known id, add to tempData
            if (item.getSharedId() == tempData.getId()) {
                tempData.add(item.getValue());
            } else {
                // or new id, write tempData, empty it, keep new id
                delegate.write(Arrays.asList(tempData));
                tempData = null;
                tempData = new AggregatedItem(item.getSharedId(), item.getValue());
            }
        }

        // check if reader exhausted, flush tempData to delegate
        if (stepExecution.getExecutionContext().containsKey(KEY_READER_EXHAUSTED)
                && (Boolean) stepExecution.getExecutionContext().get(KEY_READER_EXHAUSTED)
                && tempData != null) {
            delegate.write(Arrays.asList(tempData));
        }
    }

    public void setDelegate(ItemWriter<AggregatedItem> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return stepExecution.getExitStatus();
    }
}
